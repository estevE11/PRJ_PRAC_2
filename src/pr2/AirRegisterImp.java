package pr2;

import java.util.*;

public class AirRegisterImp implements AirRegister {

    private HashMap<Company, Collection<Aircraft>> companies;

    public AirRegisterImp() {
        this.companies = new HashMap<Company, Collection<Aircraft>>();
    }

    @Override
    public boolean addCompany(Company c) {
        if(this.companies.containsKey(c)) return false;
        this.companies.put(c, new LinkedList<Aircraft>());
        return true;
    }

    @Override
    public boolean registerAircraft(Company c, Aircraft a) {
        return false;
    }

    @Override
    public Company findCompany(AircraftID id) {
        return null;
    }

    @Override
    public SortedSet<Aircraft> registeredAircrafts(Company c) {
        return null;
    }

    @Override
    public SortedSet<Company> findCompanyByType(AircraftType t) {
        return null;
    }
}
