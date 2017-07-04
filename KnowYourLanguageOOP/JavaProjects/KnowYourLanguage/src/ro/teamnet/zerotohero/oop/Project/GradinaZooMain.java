package ro.teamnet.zerotohero.oop.Project;

import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaAnimalExceptie;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaOmException;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalPeCaleDeDisparitieException;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class GradinaZooMain {
    public static void main(String[] args) {
        AnimalZooRar animal1 = new AnimalZooRar("Pinguin");
        AnimalZooRar animal2 = new AnimalZooRar("Elefant","Africa");
        AnimalZooRar animal3 = new AnimalZooRar();

        AnimalZooFeroce animalFeroce = new AnimalZooFeroce();

        AngajatZoo angajat1 = new IngrijitorZoo();
        IngrijitorZoo angajat2 = new IngrijitorZoo();

        AngajatZoo angajat3 = new VeterinarZoo();
        VeterinarZoo angajat4 = new VeterinarZoo();

//aici se vor afisa informatiile despre animal1 si animal2 (nume si tara)
//System.out.println(animal1.getNume());

//apelul metodelor
        angajat3.lucreaza(animal1);
        angajat3.lucreaza(animal2);
        angajat3.lucreaza(animal3);
        angajat4.lucreaza(animal1);
        angajat4.lucreaza(animal2);
        angajat4.lucreaza(animal3);

        angajat1.lucreaza(animal1);
        angajat1.lucreaza(animal2);
        angajat1.lucreaza(animal3);

        angajat2.lucreaza(animal1);
        angajat2.lucreaza(animal2);
        angajat2.lucreaza(animal3);


        try {
            angajat2.lucreaza(animal1, null);
            angajat2.lucreaza(animalFeroce, null);
            angajat2.lucreaza(animal1, angajat1);
            angajat2.lucreaza(animalFeroce);

        }
        catch (AnimalPeCaleDeDisparitieException e){
           //e.printStackTrace();
        } catch (AnimalManancaAnimalExceptie animalManancaAnimalExceptie) {
            animalManancaAnimalExceptie.printStackTrace();
        }



        try {
            angajat2.lucreaza(animal1, new String("Mancare"));
            angajat2.lucreaza(animalFeroce, new String("Mancare"));
        } catch (AnimalPeCaleDeDisparitieException e) {
            e.printStackTrace();
        } catch (AnimalManancaAnimalExceptie animalManancaAnimalExceptie) {
            animalManancaAnimalExceptie.printStackTrace();
        } finally {
            System.out.println("bonus angajat 1: " + ((IngrijitorZoo)angajat1).getBonus());
            System.out.println("bonus angajat 2: " + angajat2.getBonus());
            System.out.println("bonus angajat 3: " + ((VeterinarZoo)angajat3).getBonus());
            System.out.println("bonus angajat 4: " + angajat4.getBonus());
        }

        try {
            animal1.mananca(animal2);
        } catch (AnimalManancaAnimalExceptie animalManancaAnimalExceptie) {
            animalManancaAnimalExceptie.printStackTrace();
        }

        System.out.println("Finish!");

    }
}
