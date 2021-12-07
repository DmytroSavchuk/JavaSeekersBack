package com.javaseekersback.api.model.enums;

public enum Module {
    RESOURCES(1, "Resources"),
    SYSTEM(2, "System"),
    MAINTENANCE_MANAGER(3, "Maintenance Manager"),
    STRUCTURES_ANALYST(4, "Structures Analyst"),
    FLEET_AND_EQUIPMENT_MANAGER(5, "Fleet and Equipment Manager"),
    PAVEMENT_ANALYST(6, "Pavement Analyst"),
    FACILITIES_MANAGER(7, "Facilities Manager"),
    TELECOM_AND_ITS(8, "Telecom / ITS"),
    SIGNALS_AND_ITS_MANAGER(9, "Signals and ITS Manager"),
    ROW_AND_UTILITIES(10, "ROW / Utilities"),
    SAFETY_ANALYST(11, "Safety Analyst"),
    MOBILITY_ANALYST(12, "Mobility Analyst"),
    PORTFOLIO_ANALYST(13, "Portfolio Analyst"),
    NETWORK_MANAGER(14, "Network Manager"),
    CITY_UTILITIES(15, "City utilities"),
    SIGNS_MANAGER(16, "Signs Manager"),
    STRUCTURES_INSPECTOR(17, "Structures Inspector"),
    STORMWATER_MANAGER(25, "Stormwater Manager"),
    PA_EXPRESS(26, "Pavement Analyst Express");

    private final Integer id;
    private final String name;

    Module(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Associated module id.
     *
     * @return module id
     */
    public int getId() {
        return id;
    }

    /**
     * Readable name of the module.
     *
     * @return name of the module
     */
    public String getName() {
        return name;
    }
}
