package ru.geekbrains.undertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void PresenterSaveTest(){
        ViewMainActivity mainActivity = mock(ViewMainActivity.class);
        when(mainActivity.getName()).thenReturn("Ivan");
        when(mainActivity.getSurname()).thenReturn("Ivanoff");
        IRepository repository = mock(IRepository.class);

        Presenter presenter = new Presenter(mainActivity, repository);
        presenter.save();

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(repository, times(1)).insert(argument.capture());
        assertEquals("Ivan", argument.getValue().getName());
        assertEquals("Ivanoff", argument.getValue().getSurname());
    }

}