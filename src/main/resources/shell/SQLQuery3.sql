-- ================================================
-- Template generated from Template Explorer using:
-- Create Trigger (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- See additional Create Trigger templates for more
-- examples of different Trigger statements.
--
-- This block of comments will not be included in
-- the definition of the function.
-- ================================================
use test
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================

if(exists (select * from sys.objects where name = 'tr_pushMst'))
	drop trigger tr_pushMst
go

CREATE TRIGGER [dbo].[tr_pushMst]
   ON  [dbo].[test]
   AFTER INSERT
AS 
BEGIN

	declare @params varchar(max)

	--set @params=(select name from inserted)
	
	set @params=(select id from inserted)+','+(select pin from inserted)

	print @params

	exec [dbo].[proc_pushAPI] @params

	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for trigger here

END
GO
