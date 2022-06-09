package hr.tvz.krznar.hardwareapp;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    List<Hardware> findAllThatStarts(String s);

    List<Hardware> findAllInStock();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> saveHardware(Hardware hardware);

    Optional<Hardware> updateHardware(String code, Hardware hardware);

    void deleteByCode(String code);

}
