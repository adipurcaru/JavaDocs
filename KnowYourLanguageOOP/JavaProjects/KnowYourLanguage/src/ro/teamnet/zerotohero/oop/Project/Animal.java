package ro.teamnet.zerotohero.oop.Project;

import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaAnimalExceptie;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaOmException;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public abstract class Animal {
    public abstract void mananca(Object o) throws AnimalManancaOmException, AnimalManancaAnimalExceptie;
    public abstract void seJoaca();
    public abstract void faceBaie();
    public void doarme(){
        System.out.println("Animalul doarme");
    }

    public Animal() {
        System.out.println("Animal nou");
    }
}

