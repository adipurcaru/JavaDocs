package ro.teamnet.zerotohero.oop.Project;

import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaAnimalExceptie;
import ro.teamnet.zerotohero.oop.Project.exceptions.AnimalManancaOmException;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeTaraDeOrigine;

    public AnimalZooRar(String nume, String numeTaraDeOrigine) {
        this.nume = nume;
        this.numeTaraDeOrigine = numeTaraDeOrigine;
    }

    public AnimalZooRar(String nume) {
        this.nume = nume;
    }

    public AnimalZooRar() {
    }

    public String getNume() {
        return nume;
    }

    public String getNumeTaraDeOrigine() {
        return numeTaraDeOrigine;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumeTaraDeOrigine(String numeTaraDeOrigine) {
        this.numeTaraDeOrigine = numeTaraDeOrigine;
    }

    @Override
    public void mananca(Object o) throws AnimalManancaOmException, AnimalManancaAnimalExceptie {
        if(o instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        }else if(o instanceof Animal) {
            throw new AnimalManancaAnimalExceptie();
        }else{
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }
}
