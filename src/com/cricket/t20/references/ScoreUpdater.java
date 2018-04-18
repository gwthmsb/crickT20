package com.cricket.t20.references;

import com.cricket.t20.domain.RunScored;

public interface ScoreUpdater<E> {
    public void updateScore(RunScored score);
    public E getUpdatedValue();
}
