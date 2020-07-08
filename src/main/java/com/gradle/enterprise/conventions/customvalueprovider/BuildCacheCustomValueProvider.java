package com.gradle.enterprise.conventions.customvalueprovider;

import com.gradle.scan.plugin.BuildScanExtension;
import org.gradle.api.initialization.Settings;

public class BuildCacheCustomValueProvider extends BuildScanCustomValueProvider {
    public BuildCacheCustomValueProvider(GradleEnterpriseConventions conventions) {
        super(conventions);
    }

    @Override
    public void accept(Settings settings, BuildScanExtension buildScan) {
        if (settings.getGradle().getStartParameter().isBuildCacheEnabled()) {
            buildScan.tag("CACHED");
        }
    }
}
