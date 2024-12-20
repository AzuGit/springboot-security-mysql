package com.securityApp;

import com.securityApp.persistence.entity.Permission;
import com.securityApp.persistence.entity.Role;
import com.securityApp.persistence.entity.RoleEnum;
import com.securityApp.persistence.entity.UserEntity;
import com.securityApp.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SprinbSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinbSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {  /* Create PERMISSIONS */
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();

			Permission readPermission = Permission.builder()
					.name("READ")
					.build();

			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();

			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();

			Permission refactorPermission = Permission.builder()
					.name("REFACTOR")
					.build();

			/* Create ROLES */
			Role roleAdmin = Role.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Role roleUser = Role.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			Role roleInvited = Role.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();

			Role roleDeveloper = Role.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* CREATE USERS */
			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.roles(Set.of(roleInvited))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea));

		};
	}
	}

