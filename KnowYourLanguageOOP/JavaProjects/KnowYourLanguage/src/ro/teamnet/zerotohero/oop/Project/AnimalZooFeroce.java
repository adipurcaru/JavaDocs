package ro.teamnet.zerotohero.oop.Project;

import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaAnimalExceptie;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaOmException;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class AnimalZooFeroce extends Animal {

    @Override
    public void mananca(Object o) throws AnimalManancaOmException, AnimalManancaAnimalExceptie {
        if(o instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        }else if(o instanceof Animal) {
            throw new AnimalManancaAnimalExceptie();
        }else{
            System.out.println("AnimalZooFeroce mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooFeroce se joaca");
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooFeroce face baie");
    }

    @Override
    public void doarme() {
        System.out.println("Animalul feroce vaneaza");
    }
}
