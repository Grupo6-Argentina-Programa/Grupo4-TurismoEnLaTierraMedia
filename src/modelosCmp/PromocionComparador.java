package modelosCmp;

import java.util.Comparator;

import modelos.PromocionInterface;

public class PromocionComparador implements Comparator<PromocionInterface> {

    @Override
    public int compare(PromocionInterface promocionA, PromocionInterface promocionB) {
        return (int) Math.signum(promocionB.compareTo(promocionA));
    }
}
