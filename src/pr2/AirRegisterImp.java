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
        this.companies.put(c, new TreeSet<Aircraft>());
        return true;
    }

    @Override
    public boolean registerAircraft(Company c, Aircraft a) {
        if(!this.companies.containsKey(c)) throw new UnknownCompanyException("La companyia no està registrada");

        Iterator<Company> it = this.companies.keySet().iterator();
        while(it.hasNext()) {
            Company _c = it.next();
            if(_c.equals(c)) continue;
            Collection<Aircraft> aircrafts = this.companies.get(_c);
            for(Aircraft _a : aircrafts) {
                if (_a.equals(a)) throw new DifferentCompanyException("El avió esta registrat en un altre companyia");
            }
        }

        Collection<Aircraft> aircrafts = this.companies.get(c);
        boolean added = aircrafts.add(a);
        this.companies.put(c, aircrafts);
        return added;
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
