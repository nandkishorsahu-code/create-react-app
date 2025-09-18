import React, { useState } from 'react';
import BpkText from 'bpk-component-text';
import BpkButton from 'bpk-component-button';
import { withDefaultProps } from 'bpk-react-utils';
import format from 'date-fns/format';
import BpkCalendar from 'bpk-component-calendar';
import BpkCalendarDate from 'bpk-component-calendar/src/BpkCalendarDate';

import 'bpk-stylesheets/base.css';
import './App.scss';

// Add default props for Backpack text (helpful for consistency)
const LargeText = withDefaultProps(BpkText, { textStyle: 'xl' });

function App() {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateSelect = (date) => {
    setSelectedDate(date);
  };

  return (
    <div className="App" style={{ padding: '20px' }}>
      {/* Header */}
      <LargeText tagName="h1">Flight Schedule</LargeText>

      {/* Calendar */}
      <div style={{ marginTop: '20px', marginBottom: '20px' }}>
        <BpkCalendar
          id="calendar"
          onDateSelect={handleDateSelect}
          date={selectedDate}
          formatDate={date => format(date, 'dd MMM yyyy')}
          CalendarDateComponent={BpkCalendarDate}
        />
      </div>

      {/* Continue Button */}
      <BpkButton onClick={() => alert('Continue clicked!')}>
        Continue
      </BpkButton>
    </div>
  );
}

export default App;
