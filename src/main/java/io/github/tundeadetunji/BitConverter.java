package io.github.tundeadetunji;

import java.math.BigDecimal;
import java.util.*;

public class BitConverter implements ConverterStrategy {

    private static BitConverter instance;
    public static BitConverter getInstance(){
        if(instance == null) instance = new BitConverter();
        return instance;
    }
    private BitConverter(){
        initializeValues();
    }

    private void initializeValues() {
        conversions = new HashMap<>();

        conversions.put(Unit.Bit.name() + Unit.Byte.name(), BigDecimal.valueOf(0.125));
        conversions.put(Unit.Byte.name() + Unit.Bit.name(), BigDecimal.valueOf(8));

        conversions.put(Unit.Bit.name() + Unit.Kilobyte.name(), BigDecimal.valueOf(0.000125));
        conversions.put(Unit.Kilobyte.name() + Unit.Bit.name(), BigDecimal.valueOf(8000));

        conversions.put(Unit.Bit.name() + Unit.Megabyte.name(), BigDecimal.valueOf(0.000000125));
        conversions.put(Unit.Megabyte.name() + Unit.Bit.name(), BigDecimal.valueOf(8000000));
    }

    private Map<String, BigDecimal> conversions;

    @Override
    public BigDecimal convert(double from, Unit fromUnit, Unit toUnit) throws UnsupportedOperationException {
        if (fromUnit == toUnit) return BigDecimal.valueOf(from);

        try{
            return BigDecimal.valueOf(from).multiply(conversions.get(fromUnit.name() + toUnit.name()));
        }
        catch (Exception exception){
            throw new UnsupportedOperationException("Cannot convert from " + toUnit.name() + " to " + fromUnit.name() + " yet. Try converting from Bit only.");
        }
    }


    public enum Unit {
        Bit,
        Byte,
        Kilobyte,
        Megabyte;

        public static String[] toStringArray(){
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return (String[]) Arrays.stream(DataUnit.values()).map(Enum::toString).toArray();
            }*/
            List<String> result = new ArrayList<>();
            for(Unit unit : Unit.values()){
                result.add(unit.toString());
            }
            return result.toArray(new String[Unit.values().length]);
        }

        public static String toPlural(BigDecimal count, Unit unit){
            return Objects.equals(count, BigDecimal.ONE) ? unit.name() : unit.name() + "s";
        }

        public static String toPlural(BigDecimal count, String unit){
            return Objects.equals(count, BigDecimal.ONE) ? unit : unit + "s";
        }

    }

}
interface ConverterStrategy {
    BigDecimal convert(double from, BitConverter.Unit fromUnit, BitConverter.Unit toUnit) throws UnsupportedOperationException;
}
