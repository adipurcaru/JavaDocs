package ro.teamnet.zerotohero.oop.Project;

/**
 * Created by Adrian.Purcaru on 04/07/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    Integer bonus = 0;
    @Override
    public void lucreaza(Animal animal) {

        if(animal instanceof AnimalZooFeroce){
            animal.faceBaie();
        }else{
            System.out.println("Veterinarul are grija de animal");
            calculeazaBonusSalarial();
            System.out.println("Bonusul Veterinarului este: " + getBonus());
        }
    }

    @Override
    public void calculeazaBonusSalarial() {
        bonus += valoareBonusPerAnimal * 2;
    }

    public Integer getBonus() {
        return bonus;
    }
}
