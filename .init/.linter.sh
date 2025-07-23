#!/bin/bash
cd /home/kavia/workspace/code-generation/open-audio-book-viewer-7514/audio_book_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

