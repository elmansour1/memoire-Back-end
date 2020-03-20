package com.ensp.agem;

import com.ensp.agem.dao.AuteurRepository;
import com.ensp.agem.dao.DepartementRepository;
import com.ensp.agem.dao.EnseignantRepository;
import com.ensp.agem.dao.MemoireRepository;
import com.ensp.agem.dao.ParcoursRepository;
import com.ensp.agem.dao.SpecialisationRepository;
import com.ensp.agem.data.Auteur;
import com.ensp.agem.data.Departement;
import com.ensp.agem.data.Enseignant;
import com.ensp.agem.data.Memoire;
import com.ensp.agem.data.Parcours;
import com.ensp.agem.data.Role;
import com.ensp.agem.data.Specialisation;
import com.ensp.agem.data.Utilisateur;
import com.ensp.agem.property.FileStorageProperties;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class GestionMemoireApplication implements CommandLineRunner{

    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private ParcoursRepository parcoursRepository;
    @Autowired
    private SpecialisationRepository specialisationRepository;
    @Autowired
    private MemoireRepository memoireRepository;
//    Long id = (Long)1;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
     
	public static void main(String[] args) {
		SpringApplication.run(GestionMemoireApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Auteur.class,Departement.class, Memoire.class,Parcours.class,Specialisation.class,
                Enseignant.class,Utilisateur.class,Role.class);
        
        System.out.println(memoireRepository.findAllMemoire());


//            Specilisation s = new Specialisation();
//        s = specialisationRepository.findAll();
////            System.out.println(s);
//            specialisationRepository.findAll().forEach(x->{
//                
//                System.out.println(x.getCode()+x.getParcours());
//                            System.out.println(specialisationRepository.findSpecialisationByDepartement("IG-AP")); 
//                            System.out.println(specialisationRepository.findSpecialisationByParcours("Licence"));

//
//            });
//            System.out.println(specialisationRepository.findSpecialisationByDepartement("Eolienne"));
//            System.out.println("=======================================================================");
//List<Specialisation> toto = null;            
//parcoursRepository.findAll().forEach(p->{
//                System.out.println(p.getSpecialisation());
//                
////                toto.add(parcoursRepository.findParoursBySpecialisation("Licence"));
//                 System.out.println(toto);
//
//                
////                System.out.println(parcoursRepository.findParoursBySpecialisation("Licence"));
////                System.out.println(parcoursRepository.findParoursBySpecialisation("Licence"));
//
//            });
////        System.out.println(parcoursRepository.findParoursBySpecialisation("Licence"));
//              System.out.println("=======================================================================");
//            System.out.println(specialisationRepository.findSpecialisationByParcours("Master"));
//            
            
//         List<Departement> dp;
//        dp = departementRepository.findAll();
//        System.out.println(dp);
//        System.out.println();
//        System.out.println(specialisationRepository.findSpecialisationByDepartement("Eolienne"));
        
    }

}
