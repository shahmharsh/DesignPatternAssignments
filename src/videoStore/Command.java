package videoStore;

interface Command {
	void execute() throws MovieNotFoundException, NotEnoughCopiesException, MovieAlreadyExistsException ;
}
