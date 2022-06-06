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
            /*
            No guardem el valor de "entry.getKey()" o de "entry.getValue()" en una variable ja que, al ser
            metodes molt senzills en els quals nomes s'utilitzen una vegada, no ho creiem necessari. En cas
            que els aquests valor s'utilitzessin mes d'una vegada si que potser seria mes convenient guardar-los
            en una variable per tal de poder-los reutilitzar mes facilment i que el codi sigui mes llegible.
             */
            if (entry.getKey().equals(c)) continue;
            for (Aircraft _a : entry.getValue()) {
                if (_a.equals(a)) throw new DifferentCompanyException("El avió esta registrat en un altre companyia");
            }
        }

        Collection<Aircraft> aircrafts = this.companies.get(c);
        boolean added = aircrafts.add(a);
        if(added) this.companies.put(c, aircrafts);
        return added;
    }

    @Override
    public Company findCompany(AircraftID id) {
        for (Map.Entry<Company, Collection<Aircraft>> entry : this.companies.entrySet()) {
            for (Aircraft _a : entry.getValue()) {
                if (_a.getId().equals(id)) return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public SortedSet<Aircraft> registeredAircrafts(Company c) {
        AscendingYearComparator ascendingYearComparator = new AscendingYearComparator();
        TreeSet<Aircraft> result = new TreeSet<>(ascendingYearComparator);
        for (Company _c : this.companies.keySet()) {
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
            for (Aircraft a : entry.getValue()) {
                if (a.getType().equals(t)) {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }
}
