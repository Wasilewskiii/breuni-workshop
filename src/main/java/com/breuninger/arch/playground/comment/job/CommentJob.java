package com.breuninger.arch.playground.comment.job;

import static de.otto.edison.jobs.definition.DefaultJobDefinition.cronJobDefinition;

import java.util.Optional;

import org.springframework.stereotype.Component;

import de.otto.edison.jobs.definition.JobDefinition;
import de.otto.edison.jobs.eventbus.JobEventPublisher;
import de.otto.edison.jobs.service.JobRunnable;

@Component
public class CommentJob implements JobRunnable {

  private static final String JOB_TYPE = "CommentJob";

  @Override
  public JobDefinition getJobDefinition() {
    return cronJobDefinition(
      JOB_TYPE,
      JOB_TYPE,
      "just an comment job.",
      "0 0/2 * ? * *",
      0,
      Optional.empty());
  }

  @Override
  public void execute(final JobEventPublisher eventPublisher) {
    eventPublisher.info("DING");
  }
}
