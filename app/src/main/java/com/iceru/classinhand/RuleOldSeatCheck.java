package com.iceru.classinhand;

import java.util.ArrayList;

/**
 * Created by Hongjoong on 2015-01-12.
 */
public class RuleOldSeatCheck extends Rule {

    private static final int MaxHistoryLookup = 3;
    public RuleOldSeatCheck(boolean isDefault, int priority)
    {
        super(isDefault,priority);
    }
    public ArrayList<Integer> filterSeats(Student st, ArrayList<Integer> allocatable, ArrayList<Seatplan> oldPlans, ArrayList<Seat> seatArray)
    {
        Seatplan tmpSeatplan;

        if(allocatable.size() <= 1)
            return allocatable;

        ArrayList<Integer> newAllocatable = (ArrayList<Integer>) allocatable.clone();

        /* 실제 필터 로직 추가 */

        int maxHistory = 0;
        if(oldPlans.size() < MaxHistoryLookup)
            maxHistory = oldPlans.size();
        else
            maxHistory = MaxHistoryLookup;

        // 학생 별로 앉았던 자리의 히스토리를 들고 있으므로 해당 값을 할당 가능한 자리에서 제거함
        int historyCount = 1;
        for(PersonalHistory p : st.getHistories()) {
            newAllocatable.remove(p.seatId);
            if(++historyCount > maxHistory)
                break;
            if(newAllocatable.size() <= 0)
                break;
        }

        /*
        for(int numHistory = 0 ; numHistory < maxHistory ; numHistory++)
        {
            Integer removeSeatId;
            tmpSeatplan = oldPlans.get(numHistory);
            for(int numSeats = 0 ; numSeats < tmpSeatplan.getmSeats().size() ; numSeats++)
            {
                Seat curSeat = tmpSeatplan.getmSeats().get(numSeats);
                if(st.getId() == curSeat.getItsStudent().getId()) {
                    newAllocatable.remove(new Integer(curSeat.getId()));
                    break;
                }
            }
            if(newAllocatable.size() <= 0)
                break;
        }
        */

        return newAllocatable;
    }
}
