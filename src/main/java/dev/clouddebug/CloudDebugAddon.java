package dev.clouddebug;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import meteordevelopment.meteorclient.addons.GithubRepo;
import dev.clouddebug.modules.CloudDebug;

public class CloudDebugAddon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("CloudDebugAddon");
    public static final Category CATEGORY = new Category("Cloud Debug");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Cloud Debug Addon by liago");
        Modules.get().add(new CloudDebug());
    }

    @Override
    public void onRegisterCategories() {
        Modules.get().registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "dev.clouddebug";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("liago1234567", "cloud-debug-addon");
    }
}
