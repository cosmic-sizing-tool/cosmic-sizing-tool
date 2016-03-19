package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Timer extends Model {


    @Id
    public Long timerId;

    @Column
    public Long organizationId;

    @Column
    public Long projectId;

    @Column(nullable = false)
    @Constraints.Required
    public Date startTime;

    @Column
    public Date endTime;


    public Long endCurrentTimer() {
        return 0L;
    }

    @JsonIgnore
    public static Timer getRunningTimer(Long organizationId, Long projectId) {
        return Ebean
                .find(Timer.class)
                .where()
                .eq("endTime", null)
                .eq("organizationId", organizationId)
                .eq("projectId", projectId)
                .findUnique();

    }

    @JsonProperty("runningTimeInSeconds")
    public Long timerRunningTime() {
        Date referenceEndDate;
        if (this.endTime == null) {
            referenceEndDate = new Date();
        }
        else {
            referenceEndDate = this.endTime;
        }

        return referenceEndDate.getTime() - this.startTime.getTime();
    }

    @JsonIgnore
    public static Timer startTimer(Long organizationId, Long projectId) {

        Timer currentTimer = getRunningTimer(organizationId, projectId);

        if (currentTimer == null) {
            // We create a new timer because there is no running timer

            currentTimer = new Timer();

            currentTimer.organizationId = organizationId;
            currentTimer.projectId = projectId;
            currentTimer.startTime = new Date();
            currentTimer.endTime = null;

            currentTimer.save();

        }

        System.err.println(currentTimer);
        return currentTimer;
    }

    @JsonIgnore
    public static Timer stopTimer(Long organizationId, Long projectId) {

        Timer currentTimer = getRunningTimer(organizationId, projectId);
                Ebean
                        .find(Timer.class)
                        .where()
                        .eq("endTime", null)
                        .eq("organizationId", organizationId)
                        .eq("projectId", projectId)
                        .findUnique();

        if (currentTimer == null) {
            // We create a new timer because there is no running timer

            currentTimer = new Timer();

            currentTimer.organizationId = organizationId;
            currentTimer.projectId = projectId;
            currentTimer.startTime = new Date();
            currentTimer.endTime = null;

            currentTimer.save();

        }

        System.err.println(currentTimer);
        return currentTimer;
    }

    @JsonIgnore
    public static Long sumTimers(List<Timer> timers) {

        long timeSpent = 0;
        for (Timer t : timers) {
            timeSpent += Math.abs(t.endTime.compareTo(t.startTime));
        }
        return timeSpent;
    }

    public static List<Timer> getAllTimersForProject(Long organizationId, Long projectId) {
        return Ebean.find(Timer.class)
                .where()
                .and(Expr.eq("organizationId", organizationId), Expr.eq("projectId", projectId))
                .findList();
    }

    public Timer() {
        this.startTime = new Date();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Timer{" +
                "timerId=" + timerId +
                ", organizationId=" + organizationId +
                ", projectId=" + projectId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}