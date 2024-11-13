package com.game;

import com.game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    @Autowired
    private QuestionService questionService;

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Starting createQuestionWithCorrectImage...");

            List<String[]> data = Arrays.asList(
                    // Búa til spurningar fyrir tölur
                    new String[]{"Einn", "2", "tolur", "numer_01","false", "e"},
                    new String[]{"Tveir", "2", "tolur", "numer_02","false", "t"},
                    new String[]{"Þrír", "2", "tolur", "numer_03","false", "tx"},
                    new String[]{"Fjórir", "2", "tolur", "numer_04","false", "f"},
                    new String[]{"Fimm", "2", "tolur", "numer_05","false", "f"},
                    new String[]{"Sex", "2", "tolur", "numer_06","false", "s"},
                    new String[]{"Sjö", "2", "tolur", "numer_07","false", "s"},
                    new String[]{"Átta", "2", "tolur", "numer_08","false", "ax"},
                    new String[]{"Níu", "2", "tolur", "numer_09","false", "n"},
                    new String[]{"Tíu", "2", "tolur", "numer_10","false", "t"},
                    new String[]{"Elleft", "2", "tolur", "numer_11","false", "e"},
                    new String[]{"Tólf", "2", "tolur", "numer_12","false", "t"},
                    new String[]{"Þrettán", "2", "tolur", "numer_13","false", "tx"},
                    new String[]{"Fjórtán", "2", "tolur", "numer_14","false", "f"},
                    new String[]{"Fimmtán", "2", "tolur", "numer_15","false", "f"},
                    new String[]{"Sextán", "2", "tolur", "numer_16","false", "s"},
                    new String[]{"Sautján", "2", "tolur", "numer_17","false", "s"},
                    new String[]{"Átján", "2", "tolur", "numer_18","false", "ax"},
                    new String[]{"Nítján", "2", "tolur", "numer_19","false", "n"},
                    new String[]{"Tuttugu", "2", "tolur", "numer_20","false", "t"},
                    new String[]{"Tuttugu og einn", "2", "tolur", "numer_21","false", "t"},
                    new String[]{"Tuttugu og tveir", "2", "tolur", "numer_22","false", "t"},
                    new String[]{"Tuttugu og þrír", "2", "tolur", "numer_23","false", "t"},
                    new String[]{"Tuttugu og fjórir", "2", "tolur", "numer_24","false", "t"},
                    new String[]{"Tuttugu og fimm", "2", "tolur", "numer_25","false", "t"},
                    new String[]{"Tuttugu og sex", "2", "tolur", "numer_26","false", "t"},
                    new String[]{"Tuttugu og sjö", "2", "tolur", "numer_27","false", "t"},
                    new String[]{"Tuttugu og átta", "2", "tolur", "numer_28","false", "t"},
                    new String[]{"Tuttugu og níu", "2", "tolur", "numer_29","false", "t"},
                    new String[]{"Þrjátíu", "2", "tolur", "numer_30","false", "tx"},
                    new String[]{"Þrjátíu og einn", "2", "tolur", "numer_31","false", "tx"},
                    new String[]{"Þrjátíu og tveir", "2", "tolur", "numer_32","false", "tx"},
                    new String[]{"Þrjátíu og þrír", "2", "tolur", "numer_33","false", "tx"},
                    new String[]{"Þrjátíu og fjórir", "2", "tolur", "numer_34","false", "tx"},
                    new String[]{"Þrjátíu og fimm", "2", "tolur", "numer_35","false", "tx"},
                    new String[]{"Þrjátíu og sex", "2", "tolur", "numer_36","false", "tx"},
                    new String[]{"Þrjátíu og sjö", "2", "tolur", "numer_37","false", "tx"},
                    new String[]{"Þrjátíu og átta", "2", "tolur", "numer_38","false", "tx"},
                    new String[]{"Þrjátíu og níu", "2", "tolur", "numer_39","false", "tx"},
                    new String[]{"Fjörutíu", "2", "tolur", "numer_40","false", "f"},

                    // Búa til spurningar fyrir Bókstafi
                    new String[]{"A", "1", "stafrof", "01_stafrof_a", "true", "a"},
                    new String[]{"Á", "1", "stafrof", "02_stafrof_ax", "true", "ax"},
                    new String[]{"B", "1", "stafrof", "03_stafrof_b", "true", "b"},
                    new String[]{"D", "1", "stafrof", "04_stafrof_d", "true", "d"},
                    new String[]{"Ð", "1", "stafrof", "05_stafrof_dx", "true", "dx"},
                    new String[]{"E", "1", "stafrof", "06_stafrof_e", "true", "e"},
                    new String[]{"É", "1", "stafrof", "07_stafrof_ex", "true", "ex"},
                    new String[]{"F", "1", "stafrof", "08_stafrof_f", "true", "f"},
                    new String[]{"G", "1", "stafrof", "09_stafrof_g", "true", "g"},
                    new String[]{"H", "1", "stafrof", "10_stafrof_h", "true", "h"},
                    new String[]{"I", "1", "stafrof", "11_stafrof_i", "true", "i"},
                    new String[]{"Í", "1", "stafrof", "12_stafrof_ix", "true", "ix"},
                    new String[]{"J", "1", "stafrof", "13_stafrof_j", "true", "j"},
                    new String[]{"K", "1", "stafrof", "14_stafrof_k", "true", "k"},
                    new String[]{"L", "1", "stafrof", "15_stafrof_l", "true", "l"},
                    new String[]{"M", "1", "stafrof", "16_stafrof_m", "true", "m"},
                    new String[]{"N", "1", "stafrof", "17_stafrof_n", "true", "n"},
                    new String[]{"O", "1", "stafrof", "18_stafrof_o", "true", "o"},
                    new String[]{"Ó", "1", "stafrof", "19_stafrof_ox", "true", "ox"},
                    new String[]{"P", "1", "stafrof", "20_stafrof_p", "true", "p"},
                    new String[]{"R", "1", "stafrof", "21_stafrof_r", "true", "r"},
                    new String[]{"S", "1", "stafrof", "22_stafrof_s", "true", "s"},
                    new String[]{"T", "1", "stafrof", "23_stafrof_t", "true", "t"},
                    new String[]{"U", "1", "stafrof", "24_stafrof_u", "true", "u"},
                    new String[]{"Ú", "1", "stafrof", "25_stafrof_ux", "true", "ux"},
                    new String[]{"V", "1", "stafrof", "26_stafrof_v", "true", "v"},
                    new String[]{"X", "1", "stafrof", "27_stafrof_x", "true", "x"},
                    new String[]{"Y", "1", "stafrof", "28_stafrof_y", "true", "y"},
                    new String[]{"Ý", "1", "stafrof", "29_stafrof_yx", "true", "yx"},
                    new String[]{"Þ", "1", "stafrof", "30_stafrof_tx", "true", "tx"},
                    new String[]{"Æ", "1", "stafrof", "31_stafrof_ae", "true", "ae"},
                    new String[]{"Ö", "1", "stafrof", "32_stafrof_ohx", "true", "ohx"},

                    // Búa til spurningar fyrir hluti
                    new String[]{"Bátur", "3", "hlutir", "batur","false", "b"},
                    new String[]{"Bíll", "3", "hlutir", "bill","false", "b"},
                    new String[]{"Bolti", "3", "hlutir", "bolti","false", "b"},
                    new String[]{"Bord", "3", "hlutir", "bord","false", "b"},
                    new String[]{"Fíll", "3", "hlutir", "fill","false", "f"},
                    new String[]{"Fiskur", "3", "hlutir", "fiskur","false", "f"},
                    new String[]{"Flugvél", "3", "hlutir", "flugvel","false", "f"},
                    new String[]{"Froskur", "3", "hlutir", "froskur","false", "f"},
                    new String[]{"Fugl", "3", "hlutir", "fugl","false", "f"},
                    new String[]{"Glas", "3", "hlutir", "glas","false", "g"},
                    new String[]{"Hattur", "3", "hlutir", "hattur","false", "h"},
                    new String[]{"Hjól", "3", "hlutir", "hjol","false", "h"},
                    new String[]{"Hundur", "3", "hlutir", "hundur","false", "h"},
                    new String[]{"Hús", "3", "hlutir", "hus","false", "h"},
                    new String[]{"Kastali", "3", "hlutir", "kastali","false", "k"},
                    new String[]{"Köttur", "3", "hlutir", "kottur","false", "k"},
                    new String[]{"Krókodíll", "3", "hlutir", "krokodill","false", "k"},
                    new String[]{"Lest", "3", "hlutir", "lest","false", "l"},
                    new String[]{"Letidýr", "3", "hlutir", "letidyr","false", "l"},
                    new String[]{"Ljón", "3", "hlutir", "ljon","false", "l"},
                    new String[]{"Moldvarpa", "3", "hlutir", "moldvarpa","false", "m"},
                    new String[]{"Mörgæs", "3", "hlutir", "morgaes","false", "m"},
                    new String[]{"Skógur", "3", "hlutir", "skogur","false", "s"},
                    new String[]{"Snákur", "3", "hlutir", "snakur","false", "s"},
                    new String[]{"Sveitabær", "3", "hlutir", "sveitabaer","false", "s"},
                    new String[]{"Tigrisdýr", "3", "hlutir", "tigrisdyr","false", "t"},
                    new String[]{"Ugla", "3", "hlutir", "ugla", "false", "u"},

                    // nedan_ofan_haegri_vinstri
                    // fugl
                    new String[]{"Fuglinn er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_batur","false", "f"},
                    new String[]{"Fuglinn er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_bill","false", "f"},
                    new String[]{"Fuglinn er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_haegri_bord","false", "f"},
                    new String[]{"Fuglinn er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_batur","false", "f"},
                    new String[]{"Fuglinn er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_bill","false", "f"},
                    new String[]{"Fuglinn er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_nedan_bord","false", "f"},
                    new String[]{"Fuglinn er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_batur","false", "f"},
                    new String[]{"Fuglinn er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_bill","false", "f"},
                    new String[]{"Fuglinn er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_vinstri_bord","false", "f"},
                    new String[]{"Fuglinn er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_batur","false", "f"},
                    new String[]{"Fuglinn er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_bill","false", "f"},
                    new String[]{"Fuglinn er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "fugl_ofan_bord","false", "f"},

                    // Hundur
                    new String[]{"Hundurinn er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_batur","false", "h"},
                    new String[]{"Hundurinn er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_bill","false", "h"},
                    new String[]{"Hundurinn er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_haegri_bord","false", "h"},
                    new String[]{"Hundurinn er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_batur","false", "h"},
                    new String[]{"Hundurinn er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_bill","false", "h"},
                    new String[]{"Hundurinn er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_nedan_bord","false", "h"},
                    new String[]{"Hundurinn er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_batur","false", "h"},
                    new String[]{"Hundurinn er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_bill","false", "h"},
                    new String[]{"Hundurinn er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_vinstri_bord","false", "h"},
                    new String[]{"Hundurinn er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_batur","false", "h"},
                    new String[]{"Hundurinn er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_bill","false", "h"},
                    new String[]{"Hundurinn er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "hundur_ofan_bord","false", "h"},

                    // Kisa
                    new String[]{"Kisan er hægra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_batur","false", "k"},
                    new String[]{"Kisan er hægra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_bill","false", "k"},
                    new String[]{"Kisan er hægra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_haegri_bord","false", "k"},
                    new String[]{"Kisan er fyrir neðan bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_batur","false", "k"},
                    new String[]{"Kisan er fyrir neðan bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_bill","false", "k"},
                    new String[]{"Kisan er fyrir neðan borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_nedan_bord","false", "k"},
                    new String[]{"Kisan er vinstra megin við bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_batur","false", "k"},
                    new String[]{"Kisan er vinstra megin við bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_bill","false", "k"},
                    new String[]{"Kisan er vinstra megin við borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_vinstri_bord","false", "k"},
                    new String[]{"Kisan er fyrir ofan bátinn", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_batur","false", "k"},
                    new String[]{"Kisan er fyrir ofan bílinn", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_bill","false", "k"},
                    new String[]{"Kisan er fyrir ofan borðið", "4", "nedan_ofan_haegri_vinstri", "kisa_ofan_bord","false", "k"}
            );

            for (String[] entry : data) {
                String questionName = entry[0];
                String type = entry[1]; // Convert string to integer
                String directory = entry[2];
                String fileName = entry[3];
                String isLetter = entry[4];
                String firstLetter = entry[5];
                questionService.createQuestion(questionName, Integer.parseInt(type), 1, directory, fileName, "png", Boolean.parseBoolean(isLetter), firstLetter.charAt(0));
            }


            System.out.println("createQuestionWithCorrectImage completed successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred while creating the question with the correct image.");
            e.printStackTrace();
        }
    }
}
