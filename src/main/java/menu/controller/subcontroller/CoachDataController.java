package menu.controller.subcontroller;

import menu.domain.Coach;
import menu.domain.Repository.CoachRepository;
import menu.domain.status.ApplicationStatus;
import menu.view.InputView;
import menu.view.OutputView;

public class CoachDataController implements Controllable {

    private final InputView inputview;
    private final OutputView outputView;

    public CoachDataController(InputView inputView, OutputView outputView) {
        this.inputview = inputView;
        this.outputView = outputView;
    }

    @Override
    public ApplicationStatus process() {
        outputView.printStart();
        inputview.readCoachNames().forEach(CoachRepository::add);
        for(Coach coach:CoachRepository.coaches()){
            coach.addMenuNotToEat(inputview.readMenuNotToEat(coach.getName()));
        }
        return ApplicationStatus.GIVE_RECOMMENDATION;
    }
}
