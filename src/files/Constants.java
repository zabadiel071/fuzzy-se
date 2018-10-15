package files;

import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.Point;

import java.util.ArrayList;
import java.util.Arrays;

public final class Constants {

    public static final int VARIABLE_ID_LENGTH = Byte.BYTES;
    public static final int STRING_ID_LENGTH = 50 * Character.BYTES;

    public static final FuzzyVariable OUTPUT_VARIABLE =
            new FuzzyVariable((byte)0,
                "CALIFICACION",
                new ArrayList<Label>(Arrays.asList(
                        new Label("Bajo",
                            new ArrayList<Point>(Arrays.asList(
                                new Point((byte)0,(byte)100),
                                new Point((byte)70,(byte)0)
                                )
                            )
                        ),
                        new Label("Medio",
                            new ArrayList<Point>(Arrays.asList(
                                new Point((byte) 65, (byte) 0),
                                new Point((byte) 70, (byte) 100),
                                new Point((byte) 90, (byte) 0)
                            ))
                        ),
                        new Label("Alto",
                                new ArrayList<Point>(Arrays.asList(
                                        new Point((byte) 85, (byte) 0),
                                        new Point((byte) 90, (byte) 100),
                                        new Point((byte) 100, (byte) 100)
                                ))
                        )
                    )
                )
            );
}
