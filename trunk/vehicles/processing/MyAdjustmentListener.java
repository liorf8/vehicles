/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicles.processing;
import java.awt.event.*;
import java.awt.Adjustable;
/**
 *
 * @author Niall O'Hara
 */
public class MyAdjustmentListener implements AdjustmentListener {
        // This method is called whenever the value of a scrollbar is changed,
        // either by the user or programmatically.
        //@Override
        public void adjustmentValueChanged(AdjustmentEvent evt) {
            Adjustable source = evt.getAdjustable();

            // getValueIsAdjusting() returns true if the user is currently
            // dragging the scrollbar's knob and has not picked a final value
            if (evt.getValueIsAdjusting()) {
                System.out.println("de");
                return;
            }

            // Determine which scrollbar fired the event
            int orient = source.getOrientation();
            if (orient == Adjustable.HORIZONTAL) {
               
            } else {
               
            }

            // Determine the type of event
            int type = evt.getAdjustmentType();
            switch (type) {
              case AdjustmentEvent.UNIT_INCREMENT:
                  // Scrollbar was increased by one unit
                  break;
              case AdjustmentEvent.UNIT_DECREMENT:
                  // Scrollbar was decreased by one unit
                  break;
              case AdjustmentEvent.BLOCK_INCREMENT:
                  // Scrollbar was increased by one block
                  break;
              case AdjustmentEvent.BLOCK_DECREMENT:
                  // Scrollbar was decreased by one block
                  break;
              case AdjustmentEvent.TRACK:
                  // The knob on the scrollbar was dragged
                  break;
            }

            // Get current value
            int value = evt.getValue();
        }
    }

