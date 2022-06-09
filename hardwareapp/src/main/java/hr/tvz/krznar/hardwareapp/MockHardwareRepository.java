package hr.tvz.krznar.hardwareapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
class MockHardwareRepository implements HardwareRepository {

    private LinkedList<Hardware> HARDWARE_LIST = new LinkedList<>(Arrays.asList(
            new Hardware("Asus TUF RTX 3080", "111111", 1599.0, Type.GPU, 5),
            new Hardware("EVGA XC3 RTX 3070", "222222", 1299.0, Type.GPU, 3),
            new Hardware("AMD Ryzen 5950X", "333333", 899.0, Type.CPU, 7),
            new Hardware("Samsung 980 PRO SSD 1TB", "444444", 299.0, Type.STORAGE, 11),
            new Hardware("Kingston FURY Beast DDR5 32GB", "555555", 699.0, Type.RAM, 6)
    ));

    @Override
    public List<Hardware> findAll() {
        return HARDWARE_LIST;
    }

    @Override
    public List<Hardware> findAllThatStarts(String s) {
        return null;
    }

    @Override
    public List<Hardware> findAllInStock() {
        return null;
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        Optional<Hardware> opt = HARDWARE_LIST.stream().filter(data -> Objects.equals(data.getCode(), code)).findAny();
        if (opt.isPresent()) {
            System.out.println("found");
            return opt;
        } else {
            System.out.println("nothing");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> saveHardware(final Hardware hardware) {
        System.out.println("hardware in mock rep");
        System.out.println(hardware.toString());
        for (Hardware h : HARDWARE_LIST) {
            if (Objects.equals(h.getCode(), hardware.getCode())) {
                System.out.println("code exists " + hardware.getCode() + " - " + h.getCode());
                return Optional.empty();
            }
        }
        HARDWARE_LIST.add(hardware);
        for (Hardware h : HARDWARE_LIST) {
            System.out.println(h.toString());
        }
        return Optional.of(hardware);
    }

    @Override
    public Optional<Hardware> updateHardware(String code, Hardware hardware) {
        return Optional.empty();
    }

    /*@Override
    public Optional<Hardware> patch(String code, String name) {
        for (int i = 0; i < HARDWARE_LIST.size(); i++) {
            if (Objects.equals(HARDWARE_LIST.get(i).getCode(), code)) {
                Hardware h = HARDWARE_LIST.get(i);
                h.setName(name);
                HARDWARE_LIST.set(i, h);
                return Optional.of(HARDWARE_LIST.get(i));
            }
        }
        return Optional.empty();
    }*/

    @Override
    public void deleteByCode(String code) {
        for (int i = 0; i < HARDWARE_LIST.size(); i++) {
            if (Objects.equals(HARDWARE_LIST.get(i).getCode(), code)) {
                HARDWARE_LIST.remove(i);
            }
        }
    }

}
