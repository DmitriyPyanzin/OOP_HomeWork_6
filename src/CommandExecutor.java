public interface CommandExecutor {

    String execute(String[] args) throws CommandExecutionException;

}
