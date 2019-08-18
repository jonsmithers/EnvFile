package net.ashald.envfile.providers.direnv;

import net.ashald.envfile.AbstractEnvVarsProvider;
import net.ashald.envfile.EnvFileErrorException;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DirEnvProvider extends AbstractEnvVarsProvider {

    public DirEnvProvider(boolean shouldSubstituteEnvVar) {
        super(shouldSubstituteEnvVar);
    }

    @Override
    protected @NotNull Map<String, String> getEnvVars(@NotNull Map<String, String> runConfigEnv, String path) throws EnvFileErrorException, IOException {
        System.out.println("GETTING ENV VARS!!!!");
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process p = processBuilder
                .directory(new File(path).getParentFile())
                .command("direnv", "export", "json")
                .start();

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace(); // (todo)
        }

        Map<String, String> result = new Yaml().load(p.getInputStream());
        return result;
    }
//    private String convert(InputStream inputStream) throws IOException {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
//            return br.lines().collect(Collectors.joining(System.lineSeparator()));
//        }
//    }
}
