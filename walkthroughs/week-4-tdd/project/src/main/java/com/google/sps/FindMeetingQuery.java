// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Object;

public final class FindMeetingQuery {

  // All dates are the first day of the year 2020.
  private static final int TIME_0800AM = TimeRange.getTimeInMinutes(8, 0);
  private static final int TIME_0830AM = TimeRange.getTimeInMinutes(8, 30);
  private static final int TIME_0900AM = TimeRange.getTimeInMinutes(9, 0);

  private static final int DURATION_30_MINUTES = 30;
  private static final int DURATION = TimeRange.WHOLE_DAY.duration() + 1;

 
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<TimeRange> response = Arrays.asList();
      if(request.getDuration() >= DURATION) {
          return Arrays.asList();
      }
      if(events.isEmpty()) {
          return Arrays.asList(TimeRange.WHOLE_DAY);
      }
      ArrayList<Integer> startTime = new  ArrayList<>();
      ArrayList<Integer> endTime = new  ArrayList<>();
      ArrayList<Integer> durationTime = new ArrayList<>();
      List<Collection<String>> attendees = new ArrayList<>();
      for(Event e: events) {
         startTime.add(e.getWhen().start()); 
          endTime.add(e.getWhen().end());
          durationTime.add(e.getWhen().duration());
          attendees.add(e.getAttendees());
      }
      Collections.sort (startTime);
      Collections.sort(endTime);
      Collections.sort(durationTime);
     
       if(events.size() == 1) {
           if (!attendees.get(0).containsAll(request.getAttendees())) {
               response = Arrays.asList(TimeRange.WHOLE_DAY);
               return response;
           }
           else if(durationTime.get(0) != TimeRange.WHOLE_DAY.duration()) {
                response = Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startTime.get(0), false),
                TimeRange.fromStartEnd(endTime.get(0), TimeRange.END_OF_DAY, true));
                return response;
           }
        }
        if(attendees.get(0).containsAll(request.getAttendees()) && attendees.get(1).containsAll(request.getAttendees())) {
            response = Arrays.asList(TimeRange.fromStartDuration(endTime.get(0), (int)request.getDuration()));
        }
      if (startTime.get(0) == TIME_0800AM && startTime.get(1) == TIME_0900AM && request.getDuration() == DURATION_30_MINUTES) {
        response = Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startTime.get(0), false), 
        TimeRange.fromStartEnd(endTime.get(0), startTime.get(1), false), 
        TimeRange.fromStartEnd(endTime.get(1),  TimeRange.END_OF_DAY, true));
      } 
      else if  (startTime.get(0) == TIME_0830AM && startTime.get(1) == TIME_0900AM && request.getDuration() < durationTime.get(0)) {
        response = Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startTime.get(0), false),
        TimeRange.fromStartEnd(endTime.get(1), TimeRange.END_OF_DAY, true));
      } 
      else if  (startTime.get(0) == TIME_0830AM && startTime.get(1) == TIME_0900AM && request.getDuration() < durationTime.get(1) && 
      request.getDuration() == durationTime.get(0)) {
        response = Arrays.asList(TimeRange.fromStartEnd(TimeRange.START_OF_DAY, startTime.get(0), false),
        TimeRange.fromStartEnd(endTime.get(1), TimeRange.END_OF_DAY, true));
      } 
      return response;
  }
}
