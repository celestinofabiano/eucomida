package com.geosapiens.eucomida.entity.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleTypeTest {

    @Test
    void shouldHaveAllExpectedValues() {
        assertThat(VehicleType.values()).containsExactly(
                VehicleType.BICYCLE,
                VehicleType.CAR,
                VehicleType.MOTORCYCLE
        );
    }

    @Test
    void shouldReturnEnumValueByName() {
        assertThat(VehicleType.valueOf("BICYCLE")).isEqualTo(VehicleType.BICYCLE);
        assertThat(VehicleType.valueOf("CAR")).isEqualTo(VehicleType.CAR);
        assertThat(VehicleType.valueOf("MOTORCYCLE")).isEqualTo(VehicleType.MOTORCYCLE);
    }
}
