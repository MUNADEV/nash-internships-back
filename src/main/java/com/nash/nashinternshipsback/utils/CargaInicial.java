package com.nash.nashinternshipsback.utils;

import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.model.Rol;
import com.nash.nashinternshipsback.model.enums.Roles;
import com.nash.nashinternshipsback.repository.CarreraRepository;
import com.nash.nashinternshipsback.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.logging.Logger;

@Configuration
public class CargaInicial {

    private final static Logger LOGGER = Logger.getLogger(CargaInicial.class.getName());

    @Bean
    public CommandLineRunner cargarRoles(RolRepository rolRepository, CarreraRepository carreraRepository) {
    return (args -> {
            if(!rolRepository.existsByRol(Roles.ROLE_ADMIN.name()) && !rolRepository.existsByRol(Roles.ROLE_USER.name())) {

                Rol rolAdmin = new Rol();
                Rol rolUser = new Rol();
                Rol rolPracticante = new Rol();
                Rol rolCoordinador = new Rol();

                rolAdmin.setRol(Roles.ROLE_ADMIN.name());
                rolUser.setRol(Roles.ROLE_USER.name());
                rolCoordinador.setRol(Roles.ROLE_COORDINADOR.name());
                rolPracticante.setRol(Roles.ROLE_PRACTICANTE.name());

                rolRepository.save(rolAdmin);
                rolRepository.save(rolUser);
                rolRepository.save(rolCoordinador);
                rolRepository.save(rolPracticante);

                //Carreras
                Carrera carrera1 = new Carrera();
                carrera1.setId("1");
                carrera1.setNombre("Pedagogía en Inglés");
                Carrera carrera2 = new Carrera();
                carrera2.setId("2");
                carrera2.setNombre("Periodismo");
                Carrera carrera3 = new Carrera();
                carrera3.setId("3");
                carrera3.setNombre("Sociología");
                Carrera carrera4 = new Carrera();
                carrera4.setId("4");
                carrera4.setNombre("Psicología");
                Carrera carrera5 = new Carrera();
                carrera5.setId("5");
                carrera5.setNombre("Trabajo Social");
                Carrera carrera6 = new Carrera();
                carrera6.setId("6");
                carrera6.setNombre("Pedagogía en Castellano y Comunicación");
                Carrera carrera7 = new Carrera();
                carrera7.setId("7");
                carrera7.setNombre("Pedagogía en Educación Física, Deportes y Recreación");
                Carrera carrera8 = new Carrera();
                carrera8.setId("8");
                carrera8.setNombre("Pedagogía en Historia, Geografía y Educación Cívica");
                Carrera carrera9 = new Carrera();
                carrera9.setId("9");
                carrera9.setNombre("Pedagogía en Ciencias mención Biología, Química o Física");
                Carrera carrera10 = new Carrera();
                carrera10.setId("10");
                carrera10.setNombre("Pedagogía en Matemática");
                Carrera carrera11 = new Carrera();
                carrera11.setId("11");
                carrera11.setNombre("Ingeniería Civil");
                Carrera carrera12 = new Carrera();
                carrera12.setId("12");
                carrera12.setNombre("Ingeniería Civil Física");
                Carrera carrera13 = new Carrera();
                carrera13.setId("13");
                carrera13.setNombre("Ingeniería Civil Química");
                Carrera carrera14 = new Carrera();
                carrera14.setId("14");
                carrera14.setNombre("Ingeniería Civil Informática");
                Carrera carrera15 = new Carrera();
                carrera15.setId("15");
                carrera15.setNombre("Plan Común Ingeniería Civil");
                Carrera carrera16 = new Carrera();
                carrera16.setId("16");
                carrera16.setNombre("Ingeniería Civil Industrial Mención Bioprocesos");
                Carrera carrera17 = new Carrera();
                carrera17.setId("17");
                carrera17.setNombre("Ingeniería Civil Industrial Mención Informática");
                Carrera carrera18 = new Carrera();
                carrera18.setId("18");
                carrera18.setNombre("Ingeniería Civil Industrial Mención Mecánica");
                Carrera carrera19 = new Carrera();
                carrera19.setId("19");
                carrera19.setNombre("Ingeniería Civil Ambiental");
                Carrera carrera20 = new Carrera();
                carrera20.setId("20");
                carrera20.setNombre("Ingeniería Civil Ambiental");
                Carrera carrera21 = new Carrera();
                carrera21.setId("21");
                carrera21.setNombre("Ingeniería Civil Eléctrica");
                Carrera carrera22 = new Carrera();
                carrera22.setId("22");
                carrera22.setNombre("Ingeniería Civil Electrónica");
                Carrera carrera23 = new Carrera();
                carrera23.setId("23");
                carrera23.setNombre("Ingeniería Civil Matemática");
                Carrera carrera24 = new Carrera();
                carrera24.setId("24");
                carrera24.setNombre("Ingeniería Civil Telemática");
                Carrera carrera25 = new Carrera();
                carrera25.setId("25");
                carrera25.setNombre("Ingeniería Civil Biotecnología");
                Carrera carrera26 = new Carrera();
                carrera26.setId("26");
                carrera26.setNombre("Ingeniería en Construcción");
                Carrera carrera27 = new Carrera();
                carrera27.setId("27");
                carrera27.setNombre("Ingeniería Informática");
                Carrera carrera28 = new Carrera();
                carrera28.setId("28");
                carrera28.setNombre("Bioquímica");
                Carrera carrera29 = new Carrera();
                carrera29.setId("29");
                carrera29.setNombre("Contador Público y Auditor");
                Carrera carrera30 = new Carrera();
                carrera30.setId("30");
                carrera30.setNombre("Derecho");
                Carrera carrera31 = new Carrera();
                carrera31.setId("31");
                carrera31.setNombre("Ingeniería Comercial");
                Carrera carrera32 = new Carrera();
                carrera32.setId("32");
                carrera32.setNombre("Enfermería");
                Carrera carrera33 = new Carrera();
                carrera33.setId("33");
                carrera33.setNombre("Kinesiología");
                Carrera carrera34 = new Carrera();
                carrera34.setId("34");
                carrera34.setNombre("Medicina");
                Carrera carrera35 = new Carrera();
                carrera35.setId("35");
                carrera35.setNombre("Nutrición y Dietética");
                Carrera carrera36 = new Carrera();
                carrera36.setId("36");
                carrera36.setNombre("Obstetricia y Puericultura");
                Carrera carrera37 = new Carrera();
                carrera37.setId("37");
                carrera37.setNombre("Química y Farmacia");
                Carrera carrera38 = new Carrera();
                carrera38.setId("38");
                carrera38.setNombre("Tecnología Médica");
                Carrera carrera39 = new Carrera();
                carrera39.setId("39");
                carrera39.setNombre("Terapia Ocupacional");
                Carrera carrera40 = new Carrera();
                carrera40.setId("40");
                carrera40.setNombre("Odontología");

                carreraRepository.save(carrera1);
                carreraRepository.save(carrera2);
                carreraRepository.save(carrera3);
                carreraRepository.save(carrera4);
                carreraRepository.save(carrera5);
                carreraRepository.save(carrera6);
                carreraRepository.save(carrera7);
                carreraRepository.save(carrera8);
                carreraRepository.save(carrera9);
                carreraRepository.save(carrera10);
                carreraRepository.save(carrera11);
                carreraRepository.save(carrera12);
                carreraRepository.save(carrera13);
                carreraRepository.save(carrera14);
                carreraRepository.save(carrera15);
                carreraRepository.save(carrera16);
                carreraRepository.save(carrera17);
                carreraRepository.save(carrera18);
                carreraRepository.save(carrera19);
                carreraRepository.save(carrera20);
                carreraRepository.save(carrera21);
                carreraRepository.save(carrera22);
                carreraRepository.save(carrera23);
                carreraRepository.save(carrera24);
                carreraRepository.save(carrera25);
                carreraRepository.save(carrera26);
                carreraRepository.save(carrera27);
                carreraRepository.save(carrera28);
                carreraRepository.save(carrera29);
                carreraRepository.save(carrera30);
                carreraRepository.save(carrera31);
                carreraRepository.save(carrera32);
                carreraRepository.save(carrera33);
                carreraRepository.save(carrera34);
                carreraRepository.save(carrera35);
                carreraRepository.save(carrera36);
                carreraRepository.save(carrera37);
                carreraRepository.save(carrera38);
                carreraRepository.save(carrera39);
                carreraRepository.save(carrera40);

            }
        });
    }
}
