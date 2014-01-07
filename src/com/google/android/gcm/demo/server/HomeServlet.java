/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.demo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that adds display number of devices and button to send a message.
 * <p>
 * This servlet is used just by the browser (i.e., not device) and contains the
 * main page of the demo app.
 */
@SuppressWarnings("serial")
public class HomeServlet extends BaseServlet {

  static final String ATTRIBUTE_STATUS = "status";

  /**
   * Displays the existing messages and offer the option to send a new one.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    out.print("<html><body>");
    out.print("<head>");
    out.print("  <title>GCM Demo</title>");
    out.print("  <link rel='icon' href='favicon.png'/>");
    out.print("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() +  "/css/style.css' />");
    out.print("<link rel='stylesheet' type='text/css' href='" + req.getContextPath() +  "/css/bootstrap.css' />");
    out.print("</head>");
    String status = (String) req.getAttribute(ATTRIBUTE_STATUS);
    if (status != null) {
      out.print("<div class='status'>"+status);
      out.print("</div>");
    }
    List<String> devices = Datastore.getDevices();
    
    //Common part
    out.print("<div id='container'> <h1> PFC GCM Server </h1>");
    out.print("<img src ='"+req.getContextPath() +  "/img/gcm-logo.png'>");
    out.print("<p> Welcome to the GCM server for the PFC application </p>");
    out.print("<p> Currently you have </p>");
    out.print("<h2>"+devices.size() + " device(s) </h2>");
    out.print("<p> registered! </p>");
    
    out.print("<div class ='selection'>");
    out.print("<p> Please select one of the following options </p>");
    out.print("<div class='row'>");
    out.print("<form action='sendAll' method='POST' name='form' role='form'>");
    out.print("<div class ='form-group'>");
    out.print("<div class='col-sm-4'><label for='eventType'> Event type </label></div>");
    out.print("<div class='col-sm-8'>" +
    		"<select class='form-control' id='eventType' name='eventType'>" +
    				"<option>Accident</option>" +
    				" <option>Traffic Jam</option>" +
    				" <option>Work in progress</option>" +
    				"</select>");
    out.print("</div></div>");
    out.print("<div class='clear'></div>");
    out.print("<div class ='form-group'>");
    
    out.print("<div class='col-sm-4'><label for='eventLocation'> Event Location </label></div>");
    out.print("<div class='col-sm-8'>" +
    		"<select class='form-control' id='eventLocation' name='eventLocation'>" +
    		"<option>Diagonal</option>" +
    		"<option>Campus Nord</option>" +
    		"<option>Paris</option>" +
    		"</select></div></div>");
    out.print("<div class='clear'></div>");
    
    out.print("<div class ='form-group'>");
    out.print("<div class='col-sm-4'><label for='eventExtras'> Event Extras </label></div>");
    out.print("<div class='clear'></div>");
   
    out.print("<div class='col-sm-4'>" +
    		"<div class='radio-inline'>" +
    		"<label><input type='radio' name='eventExtras' id='eventExtras1' value='none' checked>" 
    		+ "None	</label></div></div>");
    out.print("<div class='clear'></div>");
      
    out.print("<div class='col-sm-4'>" +
    		"<div class='radio-inline'>" +
    		"<label><input type='radio' name='eventExtras' id='eventExtras2' value='youtube' >" 
    		+ "Youtube	</label></div></div>");
    out.print("<div class='col-sm-8'>" +
    		"<input type='text'  name='youtube_link' id='youtube_link' placeholder='http://www.youtube.com/watch?v=iEIk3RpV6RA'>" +
    		"</div>");
    out.print("<div class='clear'></div>");
    
    out.print("<div class='col-sm-4'>" +
    		"<div class='radio-inline'>" +
    		"<label><input type='radio' name='eventExtras' id='eventExtras3' value='img' >" 
    		+ "Image</label></div></div>");
    out.print("<div class='col-sm-8'>" +
    		"<input type='text'  name='img_link' id='img_link' placeholder='http://i.imgur.com/VgJiRu2.jpg'>" +
    		"</div>");
    out.print("<div class='clear'></div>");
    out.print("</div>");
    out.print("<div class='clear'></div>");

    
    out.print("<button class='btn btn-primary' type='submit' value='Send Message' />Send Message</button>");
    out.print("</form>"+
    		"</div>");
    out.print("<div class='inv'> http://www.youtube.com/watch?v=iEIk3RpV6RA <br/>http://i.imgur.com/VgJiRu2.jpg </div>"+
    		"</div>" +
    		"</div>" +
    		"</body>" +
    		"</html>");
    
   
    
//    if (!devices.isEmpty()) {
//      out.print("<h2>No devices registered!</h2>");
//    } else {
//      out.print("<h2>" + devices.size() + " device(s) registered!</h2>");
//  
//      out.print("<form name='form' method='POST' action='sendAll'>");
//      out.print("<input type='text' name='event_type' />");
//      out.print("<input type='submit' value='Send Message' />");
//      out.print("</form>");
//    }
    
    resp.setStatus(HttpServletResponse.SC_OK);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    doGet(req, resp);
  }

}
