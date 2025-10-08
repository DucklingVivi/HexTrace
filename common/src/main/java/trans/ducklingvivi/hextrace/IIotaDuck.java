package trans.ducklingvivi.hextrace;

import at.petrak.hexcasting.api.casting.math.HexPattern;

import java.util.List;

public interface IIotaDuck {


    void markTraceable(HexPattern i);
    void markTraceable(List<HexPattern> i);
    boolean isTraced();
    void clearTrace();
    List<HexPattern> getTrace();
}