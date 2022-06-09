package hr.tvz.krznar.hardwareapp;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    List<HardwareDTO> findAllThatStarts(String s);

    List<HardwareDTO> findAllInStock();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand command);

    Optional<HardwareDTO> update(String code, HardwareCommand command);

    void deleteByCode(String code);

}
