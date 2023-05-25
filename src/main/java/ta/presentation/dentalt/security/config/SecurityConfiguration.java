package ta.presentation.dentalt.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static ta.presentation.dentalt.user.model.Role.*;
import static ta.presentation.dentalt.user.model.Permission.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/home/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()
                .requestMatchers("/operations/**").hasAnyRole(ADMIN.name(), DOCTOR.name(), PATIENT.name())
                .requestMatchers(GET, "/operations/{d$}").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/operations/all").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(POST, "/operations/create").hasAnyAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "/operations/change").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(PUT, "/operations{d$}/changeName").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(PUT, "/operations/{d$}/changeDescription").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(PUT, "/operations/{d$}/changePrice").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "/operations/{d$}/delete").hasAnyAuthority(ADMIN_DELETE.name())
                .requestMatchers(PUT, "/operations/{d$}/restore").hasAnyAuthority(ADMIN_UPDATE.name())

                .requestMatchers("/user/**").hasAnyRole(ADMIN.name(), DOCTOR.name(), PATIENT.name())
                .requestMatchers(GET, "/user/{d$}").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name())
                .requestMatchers(GET, "/user/allPatients").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name())
                .requestMatchers(GET, "/user/allDoctors").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name(), DOCTOR.name())
                .requestMatchers(GET, "/user/allAdmins").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/user/createDoctor").hasAnyAuthority(ADMIN_CREATE.name())
                .requestMatchers(POST, "/user/createAdmin").hasAnyAuthority(ADMIN_CREATE.name())

                .requestMatchers("/appointments/**").hasAnyRole()
                .requestMatchers(GET, "/appointments/{id}").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allUncompleted/{patientId}").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name())
                .requestMatchers(GET, "/appointments/allUnpaid/{patientId}").hasAnyAuthority(ADMIN_READ.name(), DOCTOR_READ.name())
                .requestMatchers(GET, "/appointments/allMyAppointments").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allMyCompleted").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allMyUncompleted").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allMyPaid").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allMyUnpaid").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/all/{date}").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(GET, "/appointments/allMylNext").hasAnyAuthority(DOCTOR_READ.name(), PATIENT_READ.name())
                .requestMatchers(POST, "/appointments/createByPatient").hasAnyAuthority(PATIENT_CREATE.name())
                .requestMatchers(POST, "/appointments/createByDoctor").hasAnyAuthority(DOCTOR_CREATE.name())
                .requestMatchers(PUT, "{id}/changeDate").hasAnyAuthority(DOCTOR_UPDATE.name())
                .requestMatchers(DELETE, "/appointments/{id}/delete").hasAnyAuthority(ADMIN_DELETE.name(), DOCTOR_DELETE.name(), PATIENT_DELETE.name())

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/home/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;
        return http.build();
    }
}
