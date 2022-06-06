package pr2;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

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

        for (Map.Entry<Company, Collection<Aircraft>> entry : this.companies.entrySet()) {
            Company _c = entry.getKey();
            if (_c.equals(c)) continue;
            Collection<Aircraft> aircrafts = entry.getValue();
            for (Aircraft _a : aircrafts) {
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
        for (Map.Entry<Company, Collection<Aircraft>> entry : this.companies.entrySet()) {
            Collection<Aircraft> aircrafts = entry.getValue();
            for (Aircraft _a : aircrafts) {
                if (_a.getId().equals(id)) return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public SortedSet<Aircraft> registeredAircrafts(Company c) {
        AscendingYearComparator ascendingYearComparator = new AscendingYearComparator();
        TreeSet<Aircraft> result = new TreeSet<>(ascendingYearComparator);
        for (Company _c : this.companies.keySet()){
            if (_c.equals(c)){
                result.addAll(this.companies.get(_c));
            }
        }
        return result;
    }

    @Override
    public SortedSet<Company> findCompanyByType(AircraftType t) {
        TreeSet<Company> result = new TreeSet<Company>();
        for (Map.Entry<Company, Collection<Aircraft>> entry : this.companies.entrySet()) {
            Company c = entry.getKey();
            Collection<Aircraft> aircrafts = this.companies.get(c);
            for (Aircraft a : aircrafts) {
                if (a.getType().equals(t)) {
                    result.add(c);
                }
            }
        }
        return result;
    }
}
