package mtr.sound.bve;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BveTrainSoundConfig {

    public String audioBaseName;

    public ConfigFile soundCfg;
    public MotorDataBase motorData;

    public BveTrainSoundConfig(ResourceManager manager, String baseName) {
        String configBaseName = "mtr:sounds/" + baseName;
        this.audioBaseName = "mtr:" + baseName + "_";
        soundCfg = new ConfigFile(readResource(manager, new ResourceLocation(configBaseName + "/sound.cfg")), this);
        if (soundCfg.motorNoiseDataType == 4) {
            motorData = new MotorData4(manager, configBaseName);
        } else {
            motorData = new MotorData5(manager, configBaseName);
        }
    }

    public static String readResource(ResourceManager manager, ResourceLocation location) {
        try {
            List<Resource> resources = manager.getResources(location);
            if (resources.size() < 1) return "";
            InputStream iStream = resources.get(0).getInputStream();
            return IOUtils.toString(iStream, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return "";
        }
    }

}
