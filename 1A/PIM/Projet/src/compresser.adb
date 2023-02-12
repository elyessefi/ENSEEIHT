with Ada.Command_Line, Text_IO, Ada.Strings.Unbounded;
use  Ada.Command_Line, Text_IO, Ada.Strings.Unbounded ;
with compression ; use compression ;
with Ada.IO_Exceptions; use Ada.IO_Exceptions;
procedure compresser is
   B : Boolean ;
   L : Integer ;

begin

    if Argument_Count = 0 then
      Raise ADA.IO_EXCEPTIONS.NAME_ERROR ;
    elsif Argument_Count = 1 then
      Compresser(To_Unbounded_string(Argument(1)), False) ;
    else
      if Argument(1) = "-b"  or Argument(1) = "--bavard" then
        B := true ;
        L := 2 ;
      else
        B := false ;
        L := 1 ;
      end if ;
      for i in L..Argument_Count loop
         Compresser(To_Unbounded_string(Argument(i)), B) ;
      end loop ;
    end if ;

end compresser ;
