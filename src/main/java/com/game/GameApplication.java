package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication{
//public class GameApplication implements CommandLineRunner {

//    @Autowired
//    private QuestionService questionService;
//
//    @Autowired
//    private LoginService loginService;
//    @Autowired
//    private SupervisorService supervisorService;
//    @Autowired
//    private AdminService adminService;

//    public void createUsers() {
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            System.out.println("Starting createAdmin...");
//            Child child = new Child("child");
//            supervisorService.createChild(child);
//            loginService.createAdmin("admin", "admin", false);
//            loginService.createAdmin("supervisor", "supervisor", true);
//            adminService.addChildToAdmin(1L, 1L);
//        } catch (Exception e) {
//            System.err.println("An error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void createQuestion(){
//        try {
//            System.out.println("Starting createQuestionWithCorrectImage...");
//            List<String[]> data = Arrays.asList(
//                    // Búa til spurningar fyrir tölur
//                    new String[]{"Einn", "2", "tolur", "numer_01","false", "e", "1"},
//                    new String[]{"Tveir", "2", "tolur", "numer_02","false", "t", "1"},
//                    new String[]{"Þrír", "2", "tolur", "numer_03","false", "tx", "1"},
//                    new String[]{"Fjórir", "2", "tolur", "numer_04","false", "f", "1"},
//                    new String[]{"Fimm", "2", "tolur", "numer_05","false", "f", "1"},
//                    new String[]{"Sex", "2", "tolur", "numer_06","false", "s", "1"},
//                    new String[]{"Sjö", "2", "tolur", "numer_07","false", "s", "1"},
//                    new String[]{"Átta", "2", "tolur", "numer_08","false", "ax", "1"},
//                    new String[]{"Níu", "2", "tolur", "numer_09","false", "n", "1"},
//                    new String[]{"Tíu", "2", "tolur", "numer_10","false", "t", "2"},
//                    new String[]{"Elleft", "2", "tolur", "numer_11","false", "e", "2"},
//                    new String[]{"Tólf", "2", "tolur", "numer_12","false", "t", "2"},
//                    new String[]{"Þrettán", "2", "tolur", "numer_13","false", "tx", "2"},
//                    new String[]{"Fjórtán", "2", "tolur", "numer_14","false", "f", "2"},
//                    new String[]{"Fimmtán", "2", "tolur", "numer_15","false", "f", "2"},
//                    new String[]{"Sextán", "2", "tolur", "numer_16","false", "s", "2"},
//                    new String[]{"Sautján", "2", "tolur", "numer_17","false", "s", "2"},
//                    new String[]{"Átján", "2", "tolur", "numer_18","false", "ax", "2"},
//                    new String[]{"Nítján", "2", "tolur", "numer_19","false", "n", "2"},
//                    new String[]{"Tuttugu", "2", "tolur", "numer_20","false", "t", "2"},
//                    new String[]{"Tuttugu og einn", "2", "tolur", "numer_21","false", "t", "2"},
//                    new String[]{"Tuttugu og tveir", "2", "tolur", "numer_22","false", "t", "2"},
//                    new String[]{"Tuttugu og þrír", "2", "tolur", "numer_23","false", "t", "2"},
//                    new String[]{"Tuttugu og fjórir", "2", "tolur", "numer_24","false", "t", "2"},
//                    new String[]{"Tuttugu og fimm", "2", "tolur", "numer_25","false", "t", "2"},
//                    new String[]{"Tuttugu og sex", "2", "tolur", "numer_26","false", "t", "2"},
//                    new String[]{"Tuttugu og sjö", "2", "tolur", "numer_27","false", "t", "2"},
//                    new String[]{"Tuttugu og átta", "2", "tolur", "numer_28","false", "t", "2"},
//                    new String[]{"Tuttugu og níu", "2", "tolur", "numer_29","false", "t", "2"},
//                    new String[]{"Þrjátíu", "2", "tolur", "numer_30","false", "tx", "2"},
//                    new String[]{"Þrjátíu og einn", "2", "tolur", "numer_31","false", "tx", "3"},
//                    new String[]{"Þrjátíu og tveir", "2", "tolur", "numer_32","false", "tx", "3"},
//                    new String[]{"Þrjátíu og þrír", "2", "tolur", "numer_33","false", "tx", "3"},
//                    new String[]{"Þrjátíu og fjórir", "2", "tolur", "numer_34","false", "tx", "3"},
//                    new String[]{"Þrjátíu og fimm", "2", "tolur", "numer_35","false", "tx", "3"},
//                    new String[]{"Þrjátíu og sex", "2", "tolur", "numer_36","false", "tx", "3"},
//                    new String[]{"Þrjátíu og sjö", "2", "tolur", "numer_37","false", "tx", "3"},
//                    new String[]{"Þrjátíu og átta", "2", "tolur", "numer_38","false", "tx", "3"},
//                    new String[]{"Þrjátíu og níu", "2", "tolur", "numer_39","false", "tx", "3"},
//                    new String[]{"Fjörutíu", "2", "tolur", "numer_40","false", "f", "3"},
//
//                    // Búa til spurningar fyrir Bókstafi
//                    new String[]{"A", "1", "stafrof", "01_stafrof_a", "true", "a", "1"},
//                    new String[]{"Á", "1", "stafrof", "02_stafrof_ax", "true", "ax", "2"},
//                    new String[]{"B", "1", "stafrof", "03_stafrof_b", "true", "b", "1"},
//                    new String[]{"D", "1", "stafrof", "04_stafrof_d", "true", "d", "1"},
//                    new String[]{"Ð", "1", "stafrof", "05_stafrof_dx", "true", "dx", "3"},
//                    new String[]{"E", "1", "stafrof", "06_stafrof_e", "true", "e", "1"},
//                    new String[]{"É", "1", "stafrof", "07_stafrof_ex", "true", "ex", "2"},
//                    new String[]{"F", "1", "stafrof", "08_stafrof_f", "true", "f", "1"},
//                    new String[]{"G", "1", "stafrof", "09_stafrof_g", "true", "g", "1"},
//                    new String[]{"H", "1", "stafrof", "10_stafrof_h", "true", "h", "1"},
//                    new String[]{"I", "1", "stafrof", "11_stafrof_i", "true", "i", "1"},
//                    new String[]{"Í", "1", "stafrof", "12_stafrof_ix", "true", "ix", "2"},
//                    new String[]{"J", "1", "stafrof", "13_stafrof_j", "true", "j", "1"},
//                    new String[]{"K", "1", "stafrof", "14_stafrof_k", "true", "k", "1"},
//                    new String[]{"L", "1", "stafrof", "15_stafrof_l", "true", "l", "1"},
//                    new String[]{"M", "1", "stafrof", "16_stafrof_m", "true", "m", "1"},
//                    new String[]{"N", "1", "stafrof", "17_stafrof_n", "true", "n", "1"},
//                    new String[]{"O", "1", "stafrof", "18_stafrof_o", "true", "o", "1"},
//                    new String[]{"Ó", "1", "stafrof", "19_stafrof_ox", "true", "ox", "2"},
//                    new String[]{"P", "1", "stafrof", "20_stafrof_p", "true", "p", "1"},
//                    new String[]{"R", "1", "stafrof", "21_stafrof_r", "true", "r", "1"},
//                    new String[]{"S", "1", "stafrof", "22_stafrof_s", "true", "s", "1"},
//                    new String[]{"T", "1", "stafrof", "23_stafrof_t", "true", "t", "1"},
//                    new String[]{"U", "1", "stafrof", "24_stafrof_u", "true", "u", "1"},
//                    new String[]{"Ú", "1", "stafrof", "25_stafrof_ux", "true", "ux", "2"},
//                    new String[]{"V", "1", "stafrof", "26_stafrof_v", "true", "v", "3"},
//                    new String[]{"X", "1", "stafrof", "27_stafrof_x", "true", "x", "3"},
//                    new String[]{"Y", "1", "stafrof", "28_stafrof_y", "true", "y", "3"},
//                    new String[]{"Ý", "1", "stafrof", "29_stafrof_yx", "true", "yx", "3"},
//                    new String[]{"Þ", "1", "stafrof", "30_stafrof_tx", "true", "tx", "3"},
//                    new String[]{"Æ", "1", "stafrof", "31_stafrof_ae", "true", "ae", "3"},
//                    new String[]{"Ö", "1", "stafrof", "32_stafrof_ohx", "true", "ohx", "3"},
//
//                    // Búa til spurningar fyrir hluti
//                    new String[]{"Bátur", "3", "hlutir", "batur","false", "b", "1"},
//                    new String[]{"Bíll", "3", "hlutir", "bill","false", "b", "1"},
//                    new String[]{"Bolti", "3", "hlutir", "bolti","false", "b", "1"},
//                    new String[]{"Bord", "3", "hlutir", "bord","false", "b", "1"},
//                    new String[]{"Fíll", "3", "hlutir", "fill","false", "f", "1"},
//                    new String[]{"Fiskur", "3", "hlutir", "fiskur","false", "f", "1"},
//                    new String[]{"Flugvél", "3", "hlutir", "flugvel","false", "f", "1"},
//                    new String[]{"Froskur", "3", "hlutir", "froskur","false", "f", "1"},
//                    new String[]{"Fugl", "3", "hlutir", "fugl","false", "f", "1"},
//                    new String[]{"Glas", "3", "hlutir", "glas","false", "g", "1"},
//                    new String[]{"Hattur", "3", "hlutir", "hattur","false", "h", "1"},
//                    new String[]{"Hjól", "3", "hlutir", "hjol","false", "h", "1"},
//                    new String[]{"Hundur", "3", "hlutir", "hundur","false", "h", "1"},
//                    new String[]{"Hús", "3", "hlutir", "hus","false", "h", "1"},
//                    new String[]{"Kastali", "3", "hlutir", "kastali","false", "k", "1"},
//                    new String[]{"Köttur", "3", "hlutir", "kottur","false", "k", "1"},
//                    new String[]{"Krókodíll", "3", "hlutir", "krokodill","false", "k", "1"},
//                    new String[]{"Lest", "3", "hlutir", "lest","false", "l", "1"},
//                    new String[]{"Letidýr", "3", "hlutir", "letidyr","false", "l", "1"},
//                    new String[]{"Ljón", "3", "hlutir", "ljon","false", "l", "1"},
//                    new String[]{"Moldvarpa", "3", "hlutir", "moldvarpa","false", "m", "1"},
//                    new String[]{"Mörgæs", "3", "hlutir", "morgaes","false", "m", "1"},
//                    new String[]{"Skógur", "3", "hlutir", "skogur","false", "s", "1"},
//                    new String[]{"Snákur", "3", "hlutir", "snakur","false", "s", "1"},
//                    new String[]{"Sveitabær", "3", "hlutir", "sveitabaer","false", "s", "1"},
//                    new String[]{"Tigrisdýr", "3", "hlutir", "tigrisdyr","false", "t", "1"},
//                    new String[]{"Ugla", "3", "hlutir", "ugla", "false", "u", "1"},
//
//                    // nedan_ofan_haegri_vinstri
//                    // fugl
//                    new String[]{"Fuglinn er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_batur","false", "f", "2"},
//                    new String[]{"Fuglinn er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_bill","false", "f", "2"},
//                    new String[]{"Fuglinn er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_bord","false", "f", "2"},
//                    new String[]{"Fuglinn er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_batur","false", "f", "1"},
//                    new String[]{"Fuglinn er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_bill","false", "f", "1"},
//                    new String[]{"Fuglinn er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_bord","false", "f", "1"},
//                    new String[]{"Fuglinn er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_batur","false", "f", "2"},
//                    new String[]{"Fuglinn er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_bill","false", "f", "2"},
//                    new String[]{"Fuglinn er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_bord","false", "f", "2"},
//                    new String[]{"Fuglinn er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_batur","false", "f", "1"},
//                    new String[]{"Fuglinn er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_bill","false", "f", "1"},
//                    new String[]{"Fuglinn er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_bord","false", "f", "1"},
//
//                    // Hundur
//                    new String[]{"Hundurinn er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_batur","false", "h", "2"},
//                    new String[]{"Hundurinn er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_bill","false", "h", "2"},
//                    new String[]{"Hundurinn er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_bord","false", "h", "2"},
//                    new String[]{"Hundurinn er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_batur","false", "h", "1"},
//                    new String[]{"Hundurinn er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_bill","false", "h", "1"},
//                    new String[]{"Hundurinn er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_bord","false", "h", "1"},
//                    new String[]{"Hundurinn er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_batur","false", "h", "2"},
//                    new String[]{"Hundurinn er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_bill","false", "h", "2"},
//                    new String[]{"Hundurinn er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_bord","false", "h", "2"},
//                    new String[]{"Hundurinn er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_batur","false", "h", "1"},
//                    new String[]{"Hundurinn er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_bill","false", "h", "1"},
//                    new String[]{"Hundurinn er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_bord","false", "h", "1"},
//
//                    // Kisa
//                    new String[]{"Kisan er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_batur","false", "k", "2"},
//                    new String[]{"Kisan er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_bill","false", "k", "2"},
//                    new String[]{"Kisan er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_bord","false", "k", "2"},
//                    new String[]{"Kisan er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_batur","false", "k", "1"},
//                    new String[]{"Kisan er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_bill","false", "k", "1"},
//                    new String[]{"Kisan er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_bord","false", "k", "1"},
//                    new String[]{"Kisan er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_batur","false", "k", "2"},
//                    new String[]{"Kisan er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_bill","false", "k", "2"},
//                    new String[]{"Kisan er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_bord","false", "k", "2"},
//                    new String[]{"Kisan er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_batur","false", "k", "1"},
//                    new String[]{"Kisan er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_bill","false", "k", "1"},
//                    new String[]{"Kisan er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_bord","false", "k", "1"}
//            );
//
//            for (String[] entry : data) {
//                String questionName = entry[0];
//                String type = entry[1]; // Convert string to integer
//                String directory = entry[2];
//                String fileName = entry[3];
//                String isLetter = entry[4];
//                String firstLetter = entry[5];
//                String level = entry[6];
//                questionService.createQuestion(questionName, Integer.parseInt(type), Integer.parseInt(level), directory, fileName, "png", Boolean.parseBoolean(isLetter), firstLetter);
//            }
//
//            System.out.println("createQuestionWithCorrectImage completed successfully.");
//        } catch (Exception e) {
//            System.err.println("An error occurred while creating the question with the correct image.");
//            e.printStackTrace();
//        }
//    }
//
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        createUsers();
//        createQuestion();
//    }

}
