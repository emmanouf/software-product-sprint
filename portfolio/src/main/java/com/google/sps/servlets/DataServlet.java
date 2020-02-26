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

package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private List<String> quotes;

  @Override
  public void init() {
    quotes = new ArrayList<>();
    quotes.add("Hapiness lies in the joy of achievement and the thrill of creative effort.  -Franklin Roosevelt");
    quotes.add("'Do not just aspire to make a living, but aspire to make a difference.  -Denzel Washington");
    quotes.add("Do not aim for success if you want it; just do what you love and believe in, and it will come naturally.  -David Frost");
    quotes.add("The key to success is to focus on goals, not obstacles.");
    quotes.add("Never give up because great things take time");
    quotes.add("Love the people who saw you when you were invisible to everyone else");
    quotes.add("If you want to live a happy life, tie it to a goal, not to people or things. -Albert Einstein");
    quotes.add("Education is the most powerful weapon which you can use to change the world.  -Nelson Mandela");
    quotes.add("We must accept finite disappointment, but never lose infinite hope.  -Martin Luther King Jr.");
    quotes.add("To win big, you sometimes have to take big risks.  -Bill Gates");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String quote = quotes.get((int) (Math.random() * quotes.size()));
    response.setContentType("text/html;");
    response.getWriter().println(quote);
  }
}
