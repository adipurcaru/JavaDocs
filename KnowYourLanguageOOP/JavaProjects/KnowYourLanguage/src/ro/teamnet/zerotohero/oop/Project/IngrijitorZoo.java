package ro.teamnet.zerotohero.oop.Project;

import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaAnimalExceptie;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaOmException;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalPeCaleDeDisparitieException;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class IngrijitorZoo implements AngajatZoo {
    Integer bonus = 0;
    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
        calculeazaBonusSalarial();
        System.out.println("Bonusul ingrijitorului este" + getBonus());
    }


    public void lucreaza(Animal animal,Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaAnimalExceptie {
        if(animal instanceof AnimalZooRar && mancare == null){
            throw new AnimalPeCaleDeDisparitieException();
        }else{
            lucreaza(animal);
            animal.mananca(mancare);
            calculeazaBonusSalarial();
            System.out.println("Bonusul ingrijitorului este" + getBonus());
        }
    }

    @Override
    public void calculeazaBonusSalarial() {
    bonus += valoareBonusPerAnimal * 3 ;
    }

    public Integer getBonus() {
        return bonus;
    }
}
