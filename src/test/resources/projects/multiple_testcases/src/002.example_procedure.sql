CREATE OR REPLACE PROCEDURE EXAMPLE_PROCEDURE(V_INOUT IN OUT INTEGER) AS
  BEGIN
    EXAMPLE$(V_INOUT, V_INOUT);
  END;