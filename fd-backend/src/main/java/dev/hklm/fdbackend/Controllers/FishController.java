package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FishController {
    private final FishdexRepository fishdexRepository;

    public FishController(FishdexRepository fishdexRepository) {
        this.fishdexRepository = fishdexRepository;
    }

    // alle Fischarten zurückgeben - findById(1) ist der erste Fishdex der beim Laden erstellt wurde
    @GetMapping("/fishdex")
    public List<Fish> getFishdex() {
        return fishdexRepository.findById(1).getFishList();
    }

    /* Frontend sendet JSON in Form des Fischobjekts {"name":"string", "location":"string", ...}
     -> wird durch @RequestBody zu einem Fish-Objekt gebaut
     -> Repository holen, fish hinzufügen, wieder speichern */
    @PostMapping("/fish")
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) {
        Fishdex fishdex = fishdexRepository.findById(1);
        fishdex.addFish(fish);
        fishdexRepository.save(fishdex);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
