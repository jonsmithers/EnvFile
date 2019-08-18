package net.ashald.envfile.providers.direnv;

import net.ashald.envfile.EnvVarsProvider;
import net.ashald.envfile.EnvVarsProviderFactory;
import org.jetbrains.annotations.NotNull;

public class DirEnvProviderFactory implements EnvVarsProviderFactory {
    @Override
    public @NotNull EnvVarsProvider createProvider(boolean shouldSubstituteEnvVar) {
        return new DirEnvProvider(shouldSubstituteEnvVar);
    }

    @Override
    public @NotNull String getTitle() {
        return "direnv";
    }
}
