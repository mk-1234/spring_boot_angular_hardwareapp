package hr.tvz.krznar.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public List<HardwareDTO> findAllThatStarts(String s) {
        return hardwareRepository.findAllThatStarts(s).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public List<HardwareDTO> findAllInStock() {
        return hardwareRepository.findAllInStock().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(final String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);

    }

    private HardwareDTO mapHardwareToDTO(final Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getPrice(), hardware.getCode(), hardware.getStock());
    }

    private Hardware mapCommandToHardware(final HardwareCommand c) {
        return new Hardware(c.getName(), c.getCode(), c.getPrice(), c.getType(), c.getStock());
    }

    @Override
    public Optional<HardwareDTO> save(final HardwareCommand command) {
        Optional<Hardware> opt = hardwareRepository.saveHardware(mapCommandToHardware(command));
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        return opt.map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> update(final String code, final HardwareCommand command) {
        return hardwareRepository.updateHardware(code, mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }
}
