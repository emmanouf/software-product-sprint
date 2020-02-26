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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Do not just aspire to make a living, but aspire to make a difference.  -Denzel Washington',
      'Hapiness lies in the joy of achievement and the thrill of creative effort.  -Franklin Roosevelt',
      'Do not aim for success if you want it; just do what you love and believe in, and it will come naturally.  -David Frost',
      'The key to success is to focus on goals, not obstacles.',
      'Never give up because great things take time', 
      'Love the people who saw you when you were invisible to everyone else',
      'If you want to live a happy life, tie it to a goal, not to people or things. -Albert Einstein',
      'Education is the most powerful weapon which you can use to change the world.  -Nelson Mandela',
      'We must accept finite disappointment, but never lose infinite hope.  -Martin Luther King Jr.',
      'To win big, you sometimes have to take big risks.  -Bill Gates'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function getRandomQuotes() {
  fetch('/data').then(response => response.text()).then((quote) => {
    document.getElementById('quote-container').innerText = quote;
  });
}

function myName() {
  fetch('/data').then(response1 => response1.text()).then((welcome) => {
    document.getElementById('name').innerText = welcome;
  });
}