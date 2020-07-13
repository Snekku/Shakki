import java.util.List;
import java.util.Random;

public class Tekoaly {
	
    private Random randomGenerator;

    public Tekoaly() { 
        randomGenerator = new Random();
    }

    protected Ruutu[] valitseSiirto(List<Ruutu> mahdollisetSiirrot, List<Ruutu> lahtoRuudut) {
        int index = randomGenerator.nextInt(mahdollisetSiirrot.size());
        Ruutu[] ruudut = new Ruutu[2];
        ruudut[0] = lahtoRuudut.get(index);
        ruudut[1] = mahdollisetSiirrot.get(index);
        return ruudut;
    }
}
