package com.gradle.enterprise.conventions.customvalueprovider;

import com.gradle.scan.plugin.BuildScanExtension;
import org.gradle.api.initialization.Settings;

public class GitInformationCustomValueProvider extends BuildScanCustomValueProvider {
    public GitInformationCustomValueProvider(Utils utils) {
        super(utils);
    }

    @Override
    public void accept(Settings settings, BuildScanExtension buildScan) {
        buildScan.background(__ -> {
            Utils.execAndGetStdout(settings.getRootDir(), "git", "status", "--porcelain")
                .ifPresent(output -> {
                    if (!output.isEmpty()) {
                        buildScan.tag("dirty");
                        buildScan.value("Git Status", output);
                    }
                });
            Utils.execAndGetStdout(settings.getRootDir(), "git", "rev-parse", "--abbrev-ref", "HEAD")
                .ifPresent(output -> buildScan.value("Git Branch Name", output));
        });
    }
}
