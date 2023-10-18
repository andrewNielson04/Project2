Andrew Nielson - niels880
Will Borgerding - borge369

Contributions:
    Andrew Nielson:
         -
    Will Borgerding:
         -

Compilation and Running:
     - After running, board will print. Follow prompts and enter move as [startCol] [startRow] [endCol] [endRow]
     - Game will end when some king is taken

Assumptions:
     - User must input move exactly as the desired format suggests, else game ends

Additional features:
     - promotePawn defaults to a queen if input is not desired
         - Not desired inputs: pawn (would just prompt again if accepted), ints, strings, spaces
     - user will be prompted for new input if they attempt an illegal move

Bugs/defects:
     - Game will end if move input doesn't strictly follow desired format
         - [startRow] [startCol] [endRow] [endCol]
             - Where each value can be any integer, positive or negative
                 - However, user will be prompted again if input is not in the bounds of the board or move is illegal

I certify that the information contained in this README
file is complete and accurate. I have both read and followed the course policies
in the ‘Academic Integrity - Course Policy’ section of the course syllabus.
 - Andrew Nielson
 - Will Borgerding
