package services;

import models.Test;

public interface testService {
    Test createTest();

    boolean updateTest(Long id);

    boolean deleteTest(Long id);
}
