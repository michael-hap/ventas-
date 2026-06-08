package gestionventas.Model;

import java.math.BigDecimal;

public enum TarifaEnvio {

    AMAZONAS(50000),
    ANTIOQUIA(30000),
    ARAUCA(45000),
    ATLANTICO(40000),
    BOLIVAR(35000),
    BOYACA(48000),
    CALDAS(15000),
    CAQUETA(30000),
    CASANARE(40000),
    CAUCA(35000),
    CESAR(35000),
    CHOCO(25000),
    CORDOBA(38000),
    CUNDINAMARCA(28000),
    GUAINIA(38000),
    GUAVIARE(40000),
    HUILA(32000),
    LA_GUAJIRA(48000),
    MAGDALENA(30000),
    META(35000),
    NARINO(40000),
    NORTE_DE_SANTANDER(38000),
    PUTUMAYO(45000),
    QUINDIO(10000),
    RISARALDA(15000),
    SAN_ANDRES_Y_PROVIDENCIA(60000),
    SANTANDER(30000),
    SUCRE(45000),
    TOLIMA(25000),
    VALLE_DEL_CAUCA(15000),
    VAUPES(40000),
    VICHADA(40000);

    private final BigDecimal costo;

    TarifaEnvio (int costo) {
        this.costo = BigDecimal.valueOf(costo);
    }

    public BigDecimal getCosto(){
        return costo;
    }
}
